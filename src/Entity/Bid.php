<?php

namespace App\Entity;

use App\Repository\BidRepository;
use DateTime;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: BidRepository::class)]
class Bid
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column]
    private ?float $bidAmount = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    private ?\DateTimeInterface $entryDate = null;

    #[ORM\Column(type: Types::TIME_MUTABLE)]
    private ?\DateTimeInterface $entryTime = null;

    #[ORM\Column(length: 255)]
    private ?string $type = null;

    #[ORM\ManyToOne(inversedBy: 'bids')]
    #[ORM\JoinColumn(nullable: false)]
    private ?User $user = null;

    #[ORM\ManyToOne(inversedBy: 'bids')]
    #[ORM\JoinColumn(nullable: false)]
    private ?Produit $produit = null;
    
    public function __construct()
    {
        $this->entryDate = new \DateTime("today");
        $this->entryTime = new \DateTime("now");
        $this->type = "Active";
    }
    public function __withID(?int $prodid)
    {
        $this->entryDate = new \DateTime("today");
        $this->entryTime = new \DateTime("now");
        $this->type = "Active";
        $this->produit = $prodid;
        return $this;
    }
    
    public static function create() {
        return new self();
    }
    
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getBidAmount(): ?float
    {
        return $this->bidAmount;
    }

    public function setBidAmount(float $bidAmount): self
    {
        $this->bidAmount = $bidAmount;

        return $this;
    }

    public function getEntryDate(): ?\DateTimeInterface
    {
        return $this->entryDate;
    }

    public function setEntryDate(\DateTimeInterface $entryDate): self
    {
        $this->entryDate = $entryDate;

        return $this;
    }

    public function getEntryTime(): ?\DateTimeInterface
    {
        return $this->entryTime;
    }

    public function setEntryTime(\DateTimeInterface $entryTime): self
    {
        $this->entryTime = $entryTime;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getProduit(): ?Produit
    {
        return $this->produit;
    }

    public function setProduit(?Produit $produit): self
    {
        $this->produit = $produit;

        return $this;
    }
}
