<?php

namespace App\Entity;


use App\Repository\ProduitRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: ProduitRepository::class)]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $name = null;

    #[ORM\Column(length: 255)]
    private ?string $brand = null;

    #[ORM\Column(length: 255)]
    private ?string $description = null;

    #[ORM\Column(length: 255)]
    private ?string $etat = null;

    #[ORM\Column(length: 255)]
    private ?string $attribute = null;

    #[ORM\Column(length: 255)]
    private ?string $value = null;

    #[ORM\Column]
    private ?float $prixDebutEnchere = null;

    #[ORM\Column]
    private ?float $prixBuyOut = null;

    #[ORM\Column]
    private ?int $livreurId;

    #[ORM\ManyToOne(inversedBy: 'produits')]
    #[ORM\JoinColumn(nullable: false)]
    private ?User $user = null;

    #[ORM\OneToMany(mappedBy: 'produit', targetEntity: Bid::class)]
    private Collection $bids;

    #[ORM\OneToMany(mappedBy: 'produit', targetEntity: Proposition::class)]
    private Collection $propositions;

    #[ORM\OneToMany(mappedBy: 'produit', targetEntity: Response::class)]
    private Collection $responses;

    public function __construct()
    {
        $this->bids = new ArrayCollection();
        $this->propositions = new ArrayCollection();
        $this->responses = new ArrayCollection();
    }

    public static function create() {
        return new self();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getBrand(): ?string
    {
        return $this->brand;
    }

    public function setBrand(string $brand): self
    {
        $this->brand = $brand;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getAttribute(): ?string
    {
        return $this->attribute;
    }

    public function setAttribute(string $attribute): self
    {
        $this->attribute = $attribute;

        return $this;
    }

    public function getValue(): ?string
    {
        return $this->value;
    }

    public function setValue(string $value): self
    {
        $this->value = $value;

        return $this;
    }

    public function getPrixDebutEnchere(): ?float
    {
        return $this->prixDebutEnchere;
    }

    public function setPrixDebutEnchere(float $prixDebutEnchere): self
    {
        $this->prixDebutEnchere = $prixDebutEnchere;

        return $this;
    }

    public function getPrixBuyOut(): ?float
    {
        return $this->prixBuyOut;
    }

    public function setPrixBuyOut(float $prixBuyOut): self
    {
        $this->prixBuyOut = $prixBuyOut;

        return $this;
    }

    public function getLivreurId(): ?int
    {
        return $this->livreurId;
    }

    public function setLivreurId(int $livreurId): self
    {
        $this->livreurId = $livreurId;

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

    /**
     * @return Collection<int, Bid>
     */
    public function getBids(): Collection
    {
        return $this->bids;
    }

    public function addBid(Bid $bid): self
    {
        if (!$this->bids->contains($bid)) {
            $this->bids->add($bid);
            $bid->setProduit($this);
        }

        return $this;
    }

    public function removeBid(Bid $bid): self
    {
        if ($this->bids->removeElement($bid)) {
            // set the owning side to null (unless already changed)
            if ($bid->getProduit() === $this) {
                $bid->setProduit(null);
            }
        }

        return $this;
    }
    public function __toString()
    {
        return (string) $this->id;
    }

    /**
     * @return Collection<int, Proposition>
     */
    public function getPropositions(): Collection
    {
        return $this->propositions;
    }

    public function addProposition(Proposition $proposition): self
    {
        if (!$this->propositions->contains($proposition)) {
            $this->propositions->add($proposition);
            $proposition->setProduit($this);
        }

        return $this;
    }

    public function removeProposition(Proposition $proposition): self
    {
        if ($this->propositions->removeElement($proposition)) {
            // set the owning side to null (unless already changed)
            if ($proposition->getProduit() === $this) {
                $proposition->setProduit(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Response>
     */
    public function getResponses(): Collection
    {
        return $this->responses;
    }

    public function addResponse(Response $response): self
    {
        if (!$this->responses->contains($response)) {
            $this->responses->add($response);
            $response->setProduit($this);
        }

        return $this;
    }

    public function removeResponse(Response $response): self
    {
        if ($this->responses->removeElement($response)) {
            // set the owning side to null (unless already changed)
            if ($response->getProduit() === $this) {
                $response->setProduit(null);
            }
        }

        return $this;
    }
}
