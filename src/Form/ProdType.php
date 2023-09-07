<?php

namespace App\Form;

use App\Entity\Produit;
use Doctrine\DBAL\Types\DateType;
use Doctrine\DBAL\Types\StringType;
use Doctrine\DBAL\Types\TimeType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Validator\Constraints\Positive;
use Symfony\Component\Validator\Constraints\Count;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Validator\Constraints\Time;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProdType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('prixDebutEnchere',NumberType::class, [
                'constraints' => [
                    new Positive([
                        'message' => 'bid amount must be positive',
                    ])/*,
                    'constraints' => array(
                        new Count(array(
                            'min' => 1,
                            'minMessage' => "Should not be blank"
                        ))
                     )*/


                ],
            ])
            ->add('prixBuyOut',NumberType::class, [
                'constraints' => [
                    new Positive([
                        'message' => 'bid amount must be positive',
                    ])/*,
                    'constraints' => array(
                        new Count(array(
                            'min' => 1,
                            'minMessage' => "Should not be blank"
                        ))
                     )*/


                ],
            ])
            ->add('user')
            ->add('etat')
        ;
    }


    
    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
        ]);
    }
}
